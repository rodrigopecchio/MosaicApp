
public class Categories {
	private String alias;
    private String title;
    
    public Categories(String alias, String title) {
    	this.alias = alias;
    	this.title = title;
    }

	public String getAlias() {
		return alias;
	}

	public String getTitle() {
		return title.substring(0, title.indexOf(" "));
	}

}
