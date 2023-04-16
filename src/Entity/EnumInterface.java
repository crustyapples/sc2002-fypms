package src.Entity;

/**
 * The EnumInterface is an interface for defining enumerated types. Classes that implement this interface
 * can provide a method to return a string representation of the item. This interface is useful for creating
 * custom enumerated types with string values that can be used in different parts of the codebase.
 */
public interface EnumInterface {
    /**
    *    Returns a string representation of the item.
    *    @return the string representation of the item.
    */
    public String toString();
}
