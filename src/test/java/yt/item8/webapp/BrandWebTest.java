//package yt.item8.webapp;
//
//import net.sourceforge.jwebunit.html.Row;
//import net.sourceforge.jwebunit.html.Table;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ResourceBundle;
//
//import static net.sourceforge.jwebunit.junit.JWebUnit.*;
//
//public class BrandWebTest {
//
//    private ResourceBundle messages;
//
//    @Before
//    public void setUp() {
//        setScriptingEnabled(false);
//        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
//        getTestContext().setResourceBundleName("messages");
//        messages = ResourceBundle.getBundle("messages");
//    }
//
//    @Before
//    public void addBrand() {
//        beginAt("/brandform");
//        assertTitleKeyMatches("brandDetail.title");
//        clickButton("save");
//        assertTitleKeyMatches("brandList.title");
//        assertKeyPresent("brand.added");
//    }
//
//    @Test
//    public void listBrands() {
//        beginAt("/brands");
//        assertTitleKeyMatches("brandList.title");
//
//        // check that table is present
//        assertTablePresent("brandList");
//    }
//
//    @Test
//    public void editBrand() {
//        beginAt("/brandform?id=" + getInsertedId());
//        clickButton("save");
//        assertTitleKeyMatches("brandDetail.title");
//    }
//
//    @Test
//    public void saveBrand() {
//        beginAt("/brandform?id=" + getInsertedId());
//        assertTitleKeyMatches("brandDetail.title");
//
//        // update some of the required fields
//        clickButton("save");
//        assertTitleKeyMatches("brandDetail.title");
//        assertKeyPresent("brand.updated");
//    }
//
//    @After
//    public void removeBrand() {
//        beginAt("/brandform?id=" + getInsertedId());
//        clickButton("delete");
//        assertTitleKeyMatches("brandList.title");
//        assertKeyPresent("brand.deleted");
//    }
//
//    /**
//     * Convenience method to get the id of the inserted record
//     *
//     * @return last id in the table
//     */
//    protected String getInsertedId() {
//        beginAt("/brands");
//        assertTablePresent("brandList");
//        Table table = getTable("brandList");
//        // Find link in last row, skip header row
//        for (int i = 1; i < table.getRows().size(); i++) {
//            Row row = table.getRows().get(i);
//            if (i == table.getRowCount() - 1) {
//                return row.getCells().get(0).getValue();
//            }
//        }
//        return "";
//    }
//
//    private void assertTitleKeyMatches(String title) {
//        assertTitleEquals(messages.getString(title) + " | " + messages.getString("webapp.name"));
//    }
//}
