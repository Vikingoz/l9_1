package serialization;

import javax.json.JsonString;

final class MyGsonString implements JsonString {
    private final String value;

    MyGsonString(String value) {
        this.value = value;
    }

    public String getString() {
        return this.value;
    }

    public CharSequence getChars() {
        return this.value;
    }

    public ValueType getValueType() {
        return ValueType.STRING;
    }

    public int hashCode() {
        return this.getString().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JsonString)) {
            return false;
        } else {
            JsonString other = (JsonString)obj;
            return this.getString().equals(other.getString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('"');

        for(int i = 0; i < this.value.length(); ++i) {
            char c = this.value.charAt(i);
            if (c >= ' ' && c <= 1114111 && c != '"' && c != '\\') {
                sb.append(c);
            } else {
                switch(c) {
                    case '\b':
                        sb.append('\\');
                        sb.append('b');
                        break;
                    case '\t':
                        sb.append('\\');
                        sb.append('t');
                        break;
                    case '\n':
                        sb.append('\\');
                        sb.append('n');
                        break;
                    case '\f':
                        sb.append('\\');
                        sb.append('f');
                        break;
                    case '\r':
                        sb.append('\\');
                        sb.append('r');
                        break;
                    case '"':
                    case '\\':
                        sb.append('\\');
                        sb.append(c);
                        break;
                    default:
                        String hex = "000" + Integer.toHexString(c);
                        sb.append("\\u").append(hex.substring(hex.length() - 4));
                }
            }
        }

        sb.append('"');
        return sb.toString();
    }
}
