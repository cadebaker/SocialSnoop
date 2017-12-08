package application;

/*******************************************************************************
 * Class that the DataTable.TableView is comprised of
 *
 * @author Logan Karney
 ******************************************************************************/
public class TableCell {

	/** Name of Cell **/
	private String name;

	/** Twitter URL of Cell **/
	private String twitterURL;

	/** Twitter Profile Name of Cell **/
	private String twitterKey;

	/** Instagram URL of CEll **/
	private String instagramURL;

	/** Instagram Token of Cell **/
	private String instagramKey;

	/** Facebook URL of Cell **/
	private String faceBookURL;

	/** Facebook Token of Cell **/
	private String faceBookKey;

	/******************************************************************************
	 * Default Constructor
	 *****************************************************************************/
	public TableCell() {
		this.name = "";
		this.twitterURL = "";
		this.twitterKey = "";
		this.instagramURL = "";
		this.instagramKey = "";
		this.faceBookURL = "";
		this.faceBookKey = "";
	}

	/******************************************************************************
	 * @param name
	 * 	Cell name
	 * @param twitterKey
	 * 	Cell Twitter tag
	 * @param instagramKey
	 * Cell instagram token
	 * @param faceBookKey
	 * Cell faceBook token
	 *****************************************************************************/
	public TableCell(String name, String twitterKey, String instagramKey, String faceBookKey) {
		this.name = name;
		this.twitterKey = twitterKey;
		this.instagramKey = instagramKey;
		this.faceBookKey = faceBookKey;
	}

	/******************************************************************************
	 * @return the profile name
	 *****************************************************************************/
	public String getName() {
		return name;
	}

	/******************************************************************************
	 * @param name
	 *            sets the profile name
	 *****************************************************************************/
	public void setName(String name) {
		this.name = name;
	}

	/******************************************************************************
	 * @return the Twitter URL
	 *****************************************************************************/
	public String getTwitterURL() {
		return twitterURL;
	}

	/******************************************************************************
	 * @param twitterURL
	 *            sets the Twitter URL
	 *****************************************************************************/
	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	/******************************************************************************
	 * @return the Instagram URL
	 *****************************************************************************/
	public String getInstagramURL() {
		return instagramURL;
	}

	/******************************************************************************
	 * @param instagramURL
	 *            sets the Instagram URL
	 *****************************************************************************/
	public void setInstagramURL(String instagramURL) {
		this.instagramURL = instagramURL;
	}

	/******************************************************************************
	 * @return the Facebook URL
	 *****************************************************************************/
	public String getFaceBookURL() {
		return faceBookURL;
	}

	/******************************************************************************
	 * @param faceBookURL
	 *            sets the Instagram URL
	 *****************************************************************************/
	public void setFaceBookURL(String faceBookURL) {
		this.faceBookURL = faceBookURL;
	}

	/******************************************************************************
	 * @return the faceBookKey
	 *****************************************************************************/
	public String getFaceBookKey() {
		return faceBookKey;
	}

	/******************************************************************************
	 * @param faceBookKey
	 *            sets the faceBookKey
	 *****************************************************************************/
	public void setFaceBookKey(String faceBookKey) {
		this.faceBookKey = faceBookKey;
	}

	/******************************************************************************
	 * @return the twitterKey
	 *****************************************************************************/
	public String getTwitterKey() {
		return twitterKey;
	}

	/******************************************************************************
	 * @param twitterKey
	 *            sets the twitterKey
	 *****************************************************************************/
	public void setTwitterKey(String twitterKey) {
		this.twitterKey = twitterKey;
	}

	/******************************************************************************
	 * @return instagramKey
	 *****************************************************************************/
	public String getInstagramKey() {
		return instagramKey;
	}

	/******************************************************************************
	 * @param instagramKey
	 * sets the InstagramKey
	 *****************************************************************************/
	public void setInstagramKey(String instagramKey) {
		this.instagramKey = instagramKey;
	}

}
