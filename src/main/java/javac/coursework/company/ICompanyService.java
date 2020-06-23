package javac.coursework.company;

/**
 * Company interface
 *
 * (c) Yaroslav Kasperovych
 * MIT License
 */

public interface ICompanyService {
    /**
     * @return top level parent
     */
    Company getTopLevelParent();

    /**
     * @return count of employees
     */
    long getEmployeeCountForCompanyAndChildren();
}