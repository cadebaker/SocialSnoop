package application;

public class TableCell {

	private String name;
	private String faceBookURL;
	private String twitterURL;
	private String instagramURL;

	public TableCell() {
		this.name = "";
		this.faceBookURL = "";
		this.twitterURL = "";
		this.instagramURL = "";
	}

	public TableCell(String name, String faceBookURL, String twitterURL, String instagramURL) {
		this.name = name;
		this.faceBookURL = faceBookURL;
		this.twitterURL = twitterURL;
		this.instagramURL = instagramURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaceBookURL() {
		return faceBookURL;
	}

	public void setFaceBookURL(String faceBookURL) {
		this.faceBookURL = faceBookURL;
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

	

}