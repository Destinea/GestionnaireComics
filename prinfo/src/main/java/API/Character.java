package API;

public class Character extends Results{
    private int ComicAppearances;
    private int FirstComicAppearanceIssueID;
    private String FirstComicAppearanceIssueName;
    private int gender;
    private String RealName;

    public Character(String name, String shortDescription, String type, String publisher, int id, String iconLink, int comicAppearances, int firstComicAppearanceIssueID, String firstComicAppearanceIssueName, int gender, String realName) {
        super(name, shortDescription, type, publisher, id, iconLink);
        ComicAppearances = comicAppearances;
        FirstComicAppearanceIssueID = firstComicAppearanceIssueID;
        FirstComicAppearanceIssueName = firstComicAppearanceIssueName;
        this.gender = gender;
        RealName = realName;
    }

    public int getComicAppearances() {
        return ComicAppearances;
    }

    public int getFirstComicAppearanceIssueID() {
        return FirstComicAppearanceIssueID;
    }

    public String getFirstComicAppearanceIssueName() {
        return FirstComicAppearanceIssueName;
    }

    public int getGender() {
        return gender;
    }

    public String getRealName() {
        return RealName;
    }
}
