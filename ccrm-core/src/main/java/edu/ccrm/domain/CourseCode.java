package edu.ccrm.domain;
import java.util.Objects;
public final class CourseCode {
    private final String prefix; private final int number;
    public CourseCode(String prefix,int number){ this.prefix=Objects.requireNonNull(prefix).toUpperCase(); this.number=number; }
    public static CourseCode parse(String code){ String[] parts = code.trim().toUpperCase().split("-"); if(parts.length!=2) throw new IllegalArgumentException("Invalid format"); return new CourseCode(parts[0], Integer.parseInt(parts[1])); }
    public String asCode(){ return prefix+"-"+number; }
    @Override public String toString(){ return asCode(); }
    @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof CourseCode)) return false; CourseCode c=(CourseCode)o; return number==c.number && prefix.equals(c.prefix); }
    @Override public int hashCode(){ return Objects.hash(prefix, number); }
}
