package hexsook.originext;

import java.util.Collections;
import java.util.List;

/**
 * A utility class for handling pagination operations on lists.
 * <p>
 * This class provides methods to split a list into pages of a specified size and
 * retrieve either a particular page of data or the page number of a specified element.
 * </p>
 */
public class Pager {

    /**
     * Splits the list into pages of the specified size and returns the sublist
     * of the specified page number.
     *
     * @param data the list to be paginated
     * @param size the number of elements per page
     * @param page the page number to retrieve (1-based index)
     * @param <T> the type of elements in the list
     * @return the sublist representing the specified page, or an empty list if the page number is out of bounds
     */
    public static <T> List<T> getPage(List<T> data, int size, int page) {
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, data.size());

        if (startIndex >= data.size()) {
            return Collections.emptyList();
        } else {
            return data.subList(startIndex, endIndex);
        }
    }

    /**
     * Returns the page number in which the specified element is found,
     * when the list is split into pages of the specified size.
     *
     * @param data the list to search
     * @param obj the element to find
     * @param size the number of elements per page
     * @param <T> the type of elements in the list
     * @return the 1-based page number containing the element, or 1 if not found
     */
    public static <T> int getPageAt(List<T> data, T obj, int size) {
        int totalPage = calculatePages(data.size(), size);
        for (int i = 1; i <= totalPage; i++) {
            List<T> current = getPage(data, size, i);
            if (current.contains(obj)) return i;
        }
        return 1;
    }

    /**
     * Calculates the total number of pages needed to hold a given number of elements,
     * based on the specified page size.
     *
     * @param amount the total number of elements
     * @param size the number of elements per page
     * @return the total number of pages
     */
    public static int calculatePages(int amount, int size) {
        return (int) Math.ceil((double) amount / size);
    }

}

