package javac.coursework;

import javac.coursework.company.Company;
import javac.coursework.glossary.Glossary;

import java.util.Map;
import java.util.TreeSet;

/**
 * Program entry point
 *
 * (c) Yaroslav Kasperovych
 * MIT License
 */

public class Main {

    public static void main(String[] args) {
        Glossary glossary = new Glossary();

        // Words with occurrences
        TreeSet<Map.Entry<String, Integer>> occurrences = glossary.getDistinctWords();
        // Neural network recognized names
        TreeSet<String> names = glossary.getNames();

        // Print all unique words with their frequencies
        occurrences.forEach((entry) -> {
            System.out.println(String.format("%s %s", entry.getKey(), entry.getValue()));
        });

        // Print all names
        names.forEach((name) -> {
            System.out.println(name);
        });

        // 2nd task
        Company lawyer = new Company("Lawyer Inc.", 5);
        Company laundry = new Company("LLC Laundry", 5, lawyer);
        Company google = new Company("Google Inc.", 12, laundry);
        Company ibm = new Company("IBM Inc.", 380);
        Company facebook = new Company("Facebook", 50, laundry);

        Company[] companies = { lawyer, laundry, google, ibm, facebook };

        for (Company company : companies) {
            System.out.println(
                    String.format(
                            "Company name: %s, Total employees count: %s, Top-level company name: %s",
                            company.getName(),
                            company.getEmployeeCountForCompanyAndChildren(),
                            company.getTopLevelParent().getName()));
        }
    }
}
