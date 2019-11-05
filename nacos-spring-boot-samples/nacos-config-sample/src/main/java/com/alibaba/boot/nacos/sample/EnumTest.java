package com.alibaba.boot.nacos.sample;

public class EnumTest {
	
	public enum King{
	    ELVIS
	}

	public static void main(final String[] args){
		final Enum<?> theOneAndOnly = findEnumValue(King.class, "ELVIS");
	    System.out.println(theOneAndOnly.name());
	}
	
	public static <E extends Enum<E>> E of(Class<E> clazz, String name) {
        E value = Enum.valueOf(clazz, name);
        return value;
    }
	
	private static Enum<?> findEnumValue(Class<? extends Enum<?>> enumType, String value) {
		Enum<?>[] enumConstants= enumType.getEnumConstants();
		for(Enum<?> e : enumConstants) {
			if(e.name().equals(value)) {
				return e;
			}
		}
	    return null;
//		return Arrays.stream(enumType.getEnumConstants())
//	                 .filter(e -> e.name().equals(value))
//	                 .findFirst()
//	                 .orElse(null);
		
	}

}
