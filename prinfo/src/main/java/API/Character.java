package API;

public class Character extends Results{
    private final int ComicAppearances;
    private final int FirstComicAppearanceIssueID;
    private final String FirstComicAppearanceIssueName;

    //Gender : male = 1, TODO Female = ?? Other = ??
    private final int gender;
    private final String RealName;

    public Character(String name, String shortDescription, String type, int id, String iconLink, int comicAppearances, int firstComicAppearanceIssueID, String firstComicAppearanceIssueName, int gender, String realName,String HTMLDescription, String bigIconLink) {
        super(name, shortDescription, type, id, iconLink, HTMLDescription, bigIconLink);
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
