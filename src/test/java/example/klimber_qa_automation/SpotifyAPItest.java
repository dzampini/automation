package example.klimber_qa_automation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



// test automatizado de resultados de busqueda de canciones en spotify.
//Para obtener los resultados se debe actualizar el token desde esta web: https://developer.spotify.com/
// si el token no se actualiza arroja un 400 y lo muestra en consola, y si existe otro error lo captura y lo muestra en consola

public class SpotifyAPItest {

    private static final String token = "BQDrXWKhoq-OCKlzTCKJ-dh4bB3EpWtAfzl9UL6-PdJfgfwIsq78h4KpWMI5CfVc87p1WQ8g6E76otQwgIq2X8uJLS-4Bx5-cxiceJIwWidwkln5f92CRjvtAVV60uW5SFu6XvNf9LD6iyrWxr47gv8c20IFvqXbS4pZkNFH-rL_1jpTt-_NEpAjf24bWgQdDw1w21J8I-T-igYGJWN0t6yGMDK0fR7DgRLP7JjJoM78dhJKzCVvvs-AOh8dGmw7Ww";

    public static void main(String[] args) throws JSONException {
        try {
            JSONArray topTracks = getTopTracks();
            for (int i = 0; i < topTracks.length(); i++) {
                JSONObject track = topTracks.getJSONObject(i);
                String name = track.getString("name");
                JSONArray artists = track.getJSONArray("artists");
                StringBuilder artistsNames = new StringBuilder();
                for (int j = 0; j < artists.length(); j++) {
                    JSONObject artist = artists.getJSONObject(j);
                    artistsNames.append(artist.getString("name"));
                    if (j < artists.length() - 1) {
                        artistsNames.append(", ");
                    }
                }
                System.out.println(name + " by " + artistsNames);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getTopTracks() throws IOException, JSONException {
        String endpoint = "v1/me/top/tracks?time_range=long_term&limit=5";
        @SuppressWarnings("deprecation")
        URL url = new URL("https://api.spotify.com/" + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONArray("items");
        } else {
            System.out.println("GET request failed: " + responseCode);
            return new JSONArray();
        }
    }

}

