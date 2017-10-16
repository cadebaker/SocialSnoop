package application;

public class TableCell {

	private String name;
	private String twitterURL;
	private String twitterName;
	private String instagramURL;
	private String instagramKey;
	private String faceBookURL;
	private String faceBookKey;

	public TableCell() {
		this.name = "";
		this.twitterURL = "";
		this.twitterName = "";
		this.instagramURL = "";
		this.instagramKey = "";
		this.faceBookURL = "";
		this.faceBookKey = "";
	}

	public TableCell(String name, String twitterName, String instagramKey, String faceBookKey) {
		this.name = name;
		this.twitterName = twitterName;
		this.instagramKey = instagramKey;
		this.faceBookKey = faceBookKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTwitterURL() {
		return twitterURL;
	}

	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	public String getInstagramURL() {
		return instagramURL;
	}

	public void setInstagramURL(String instagramURL) {
		this.instagramURL = instagramURL;
	}
	
	public String getFaceBookURL() {
		return faceBookURL;
	}

	public void setFaceBookURL(String faceBookURL) {
		this.faceBookURL = faceBookURL;
	}

	public String getFaceBookKey() {
		return faceBookKey;
	}

	public void setFaceBookKey(String faceBookKey) {
		this.faceBookKey = faceBookKey;
	}

	public String getTwitterName() {
		return twitterName;
	}

	public void setTwitterName(String twitterKey) {
		this.twitterName = twitterKey;
	}

	public String getInstagramKey() {
		return instagramKey;
	}

	public void setInstagramKey(String instagramKey) {
		this.instagramKey = instagramKey;
	}

	

}
