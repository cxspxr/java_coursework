package javac.coursework.company;

import java.util.ArrayList;

/**
 * Company class
 *
 * (c) Yaroslav Kasperovych
 * MIT License
 */

public class Company implements ICompanyService {
    private Company parent;
    private ArrayList<Company> children = new ArrayList<>();
    private long employeesCount;
    private String name;


    /**
     * Company name getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Company name setter
     * @param name - to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Company constructor with parent
     *
     * @param name - of a company
     * @param employeesCount - in a company
     * @param parent - parent company
     */
    public Company(String name, long employeesCount, Company parent) {
        this.parent = parent;
        this.employeesCount = employeesCount;
        this.name = name;

        parent.addChild(this);
    }

    /**
     * Company constructor without parent
     *
     * @param name
     * @param employeesCount
     */
    public Company(String name, long employeesCount) {
        this.employeesCount = employeesCount;
        this.name = name;
    }

    /**
     * Parent
     *
     * @return
     */
    public Company getParent() {
        return parent;
    }

    public void addChild(Company company) {
        this.children.add(company);
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public ArrayList<Company> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Company> children) {
        this.children = children;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    @Override
    public Company getTopLevelParent() {
        Company topLevelParent = this;

        while(topLevelParent.getParent() != null) {
            topLevelParent = topLevelParent.getParent();
        }

        return topLevelParent;
    }

    @Override
    public String toString() {
        return "Company{" +
                "parent=" + parent +
                ", children=" + children +
                ", employeesCount=" + employeesCount +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren() {
        long totalEmployeesCounter = employeesCount;

        for (Company child : children) {
            totalEmployeesCounter += child.getEmployeeCountForCompanyAndChildren();
        }

        return totalEmployeesCounter;
    }
}