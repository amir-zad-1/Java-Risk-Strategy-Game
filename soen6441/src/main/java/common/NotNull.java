package common;

/**
 * <tt>An element which claims @NotNull should not accept the null values</tt>
 * <tt>A method which claims @NotNull should not return the null values</tt>
 * @author Manohar Gunturu
 */
public @interface NotNull {

	
    /**
     * If a string claims it is NotNull and its value always gives empty string
     * @return the empty string value to avoid problems with nulls
     */
    String value() default "";
	
}
