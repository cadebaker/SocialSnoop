package application;

public class TableCell {

	private String name;
	private String twitterURL;
	private String instagramURL;
	private String faceBookURL;
	private String faceBookKey;

	public TableCell() {
		this.name = "";
		this.twitterURL = "";
		this.instagramURL = "";
		this.faceBookURL = "";
		this.faceBookKey = "";
	}

	public TableCell(String name, String twitterURL, String instagramURL, String faceBookURL, String faceBookKey) {
		this.name = name;
		this.twitterURL = twitterURL;
		this.instagramURL = instagramURL;
		this.faceBookURL = faceBookURL;
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

	

}
