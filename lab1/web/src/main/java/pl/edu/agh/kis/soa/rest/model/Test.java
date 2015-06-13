package pl.edu.agh.kis.soa.rest.model;

/**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 5/5/15
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    private String attr1 = "Pierwszy";
    private Long attr2 = new Long(4);
    private String [] tab = {"a", "b", "c"};

    public Test() {
    }

    public Test(String attr1) {
        this.attr1 = attr1;
    }

    public Test(String attr1, Long attr2) {
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    public String[] getTab() {
        return tab;
    }

    public void setTab(String[] tab) {
        this.tab = tab;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public Long getAttr2() {
        return attr2;
    }

    public void setAttr2(Long attr2) {
        this.attr2 = attr2;
    }


}
