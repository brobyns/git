package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class FetchData extends AsyncTask<Void, Void, Void>{

	private ConnectivityManager connectivityManager;
	private NetworkInfo netwerkInfo;

	private Context context;

	private boolean isConnected = false;

	public static final String GAME = "game";

	public FetchData(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		checkIfConnected();
	}

	@Override
	protected Void doInBackground(Void...params) {
	/*	List<Word> words = new ArrayList<Word>();

		if(!isConnected) {		//OFFLINE

			Resources resources = context.getResources();

			XmlResourceParser parser = resources.getXml(R.xml.words);

			try {
				int counter = 1, points = 0;
				String text = null, category = null;

				while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
					if(parser.getEventType() == XmlResourceParser.TEXT){
						if(counter == 1) {	
							text = parser.getText();
						} else if (counter == 2) {
							category = parser.getText();
						} else if (counter == 3) {
							points = Integer.parseInt(parser.getText());
						}
						counter++;

						if(counter == 4) {
							counter = 1;
							words.add(new Word(text, category, points));
						}
					}
					parser.next();
				}
				game.setWords(words);
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			parser.close();

		} else {		  //ONLINE

			JsonParser parser = new JsonParser();
			String json = parser.getJSONFromUrl("https://u0047590.webontwerp.khleuven.be/php/fetchGuessWords.php");

			if (json != null) {
				try {
					JSONArray array = new JSONArray(json);

					for(int i = 0; i < array.length(); i++) {
						JSONObject object = new JSONObject(array.getString(i));
						Word word = new Word(object.getString("guessword").toUpperCase(), object.getString("type").substring(0,1).toUpperCase() + object.getString("type").substring(1), object.getString("guessword").length() * 10);
						words.add(word);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			game.setWords(words);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);

		connectivityManager = null;
		netwerkInfo = null;
	}
	
	public void checkIfConnected(){
		connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		netwerkInfo = connectivityManager.getActiveNetworkInfo();	

		if(netwerkInfo != null && netwerkInfo.isConnected()) {
			isConnected = true;
		}
	}
	
	public boolean isConnected(){
		return isConnected;
	}
	
	
}