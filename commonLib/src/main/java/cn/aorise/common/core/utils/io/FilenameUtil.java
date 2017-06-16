/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.aorise.common.core.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public class FilenameUtil {

    public static final char EXTENSION_SEPARATOR = '.';

    public static final String EXTENSION_SEPARATOR_STR = Character.toString(EXTENSION_SEPARATOR);

    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * The system separator character.
     */
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    /**
     * The separator character that is the opposite of the system separator.
     */
    private static final char OTHER_SEPARATOR;

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = UNIX_SEPARATOR;
        } else {
            OTHER_SEPARATOR = WINDOWS_SEPARATOR;
        }
    }

    /**
     * Instances should NOT be constructed in standard programming.
     */
    public FilenameUtil() {
        super();
    }

    //-----------------------------------------------------------------------

    /**
     * Determines if Windows file system is in use.
     *
     * @return true if the system is Windows
     */
    public static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == WINDOWS_SEPARATOR;
    }

    //-----------------------------------------------------------------------

    /**
     * Checks if the character is a separator.
     *
     * @param ch the character to check
     * @return true if it is a separator character
     */
    private static boolean isSeparator(char ch) {
        return ch == UNIX_SEPARATOR || ch == WINDOWS_SEPARATOR;
    }

    //-----------------------------------------------------------------------
    public static String normalize(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, true);
    }


    public static String normalize(String filename, boolean unixSeparator) {
        char separator = unixSeparator ? UNIX_SEPARATOR : WINDOWS_SEPARATOR;
        return doNormalize(filename, separator, true);
    }

    //-----------------------------------------------------------------------

    public static String normalizeNoEndSeparator(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
        char separator = unixSeparator ? UNIX_SEPARATOR : WINDOWS_SEPARATOR;
        return doNormalize(filename, separator, false);
    }

    /**
     * Internal method to perform the normalization.
     *
     * @param filename      the filename
     * @param separator     The separator character to use
     * @param keepSeparator true to keep the final separator
     * @return the normalized filename
     */
    private static String doNormalize(String filename, char separator, boolean keepSeparator) {
        if (filename == null) {
            return null;
        }
        int size = filename.length();
        if (size == 0) {
            return filename;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }

        char[] array = new char[size + 2];  // +1 for possible extra slash, +2 for arraycopy
        filename.getChars(0, filename.length(), array, 0);

        // fix separators throughout
        char otherSeparator = separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == otherSeparator) {
                array[i] = separator;
            }
        }

        // add extra separator on the end to simplify code below
        boolean lastIsDirectory = true;
        if (array[size - 1] != separator) {
            array[size++] = separator;
            lastIsDirectory = false;
        }

        // adjoining slashes
        for (int i = prefix + 1; i < size; i++) {
            if (array[i] == separator && array[i - 1] == separator) {
                System.arraycopy(array, i, array, i - 1, size - i);
                size--;
                i--;
            }
        }

        // dot slash
        for (int i = prefix + 1; i < size; i++) {
            if (array[i] == separator && array[i - 1] == '.' &&
                    (i == prefix + 1 || array[i - 2] == separator)) {
                if (i == size - 1) {
                    lastIsDirectory = true;
                }
                System.arraycopy(array, i + 1, array, i - 1, size - i);
                size -= 2;
                i--;
            }
        }

        // double dot slash
        outer:
        for (int i = prefix + 2; i < size; i++) {
            if (array[i] == separator && array[i - 1] == '.' && array[i - 2] == '.' &&
                    (i == prefix + 2 || array[i - 3] == separator)) {
                if (i == prefix + 2) {
                    return null;
                }
                if (i == size - 1) {
                    lastIsDirectory = true;
                }
                int j;
                for (j = i - 4; j >= prefix; j--) {
                    if (array[j] == separator) {
                        // remove b/../ from a/b/../c
                        System.arraycopy(array, i + 1, array, j + 1, size - i);
                        size -= i - j;
                        i = j + 1;
                        continue outer;
                    }
                }
                // remove a/../ from a/../c
                System.arraycopy(array, i + 1, array, prefix, size - i);
                size -= i + 1 - prefix;
                i = prefix + 1;
            }
        }

        if (size <= 0) {  // should never be less than 0
            return "";
        }
        if (size <= prefix) {  // should never be less than prefix
            return new String(array, 0, size);
        }
        if (lastIsDirectory && keepSeparator) {
            return new String(array, 0, size);  // keep trailing separator
        }
        return new String(array, 0, size - 1);  // lose trailing separator
    }

    //-----------------------------------------------------------------------

    public static String concat(String basePath, String fullFilenameToAdd) {
        int prefix = getPrefixLength(fullFilenameToAdd);
        if (prefix < 0) {
            return null;
        }
        if (prefix > 0) {
            return normalize(fullFilenameToAdd);
        }
        if (basePath == null) {
            return null;
        }
        int len = basePath.length();
        if (len == 0) {
            return normalize(fullFilenameToAdd);
        }
        char ch = basePath.charAt(len - 1);
        if (isSeparator(ch)) {
            return normalize(basePath + fullFilenameToAdd);
        } else {
            return normalize(basePath + '/' + fullFilenameToAdd);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Converts all separators to the Unix separator of forward slash.
     *
     * @param path the path to be changed, null ignored
     * @return the updated path
     */
    public static String separatorsToUnix(String path) {
        if (path == null || path.indexOf(WINDOWS_SEPARATOR) == -1) {
            return path;
        }
        return path.replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR);
    }

    /**
     * Converts all separators to the Windows separator of backslash.
     *
     * @param path the path to be changed, null ignored
     * @return the updated path
     */
    public static String separatorsToWindows(String path) {
        if (path == null || path.indexOf(UNIX_SEPARATOR) == -1) {
            return path;
        }
        return path.replace(UNIX_SEPARATOR, WINDOWS_SEPARATOR);
    }

    /**
     * Converts all separators to the system separator.
     *
     * @param path the path to be changed, null ignored
     * @return the updated path
     */
    public static String separatorsToSystem(String path) {
        if (path == null) {
            return null;
        }
        if (isSystemWindows()) {
            return separatorsToWindows(path);
        } else {
            return separatorsToUnix(path);
        }
    }

    //-----------------------------------------------------------------------

    public static int getPrefixLength(String filename) {
        if (filename == null) {
            return -1;
        }
        int len = filename.length();
        if (len == 0) {
            return 0;
        }
        char ch0 = filename.charAt(0);
        if (ch0 == ':') {
            return -1;
        }
        if (len == 1) {
            if (ch0 == '~') {
                return 2;  // return a length greater than the input
            }
            return isSeparator(ch0) ? 1 : 0;
        } else {
            if (ch0 == '~') {
                int posUnix = filename.indexOf(UNIX_SEPARATOR, 1);
                int posWin = filename.indexOf(WINDOWS_SEPARATOR, 1);
                if (posUnix == -1 && posWin == -1) {
                    return len + 1;  // return a length greater than the input
                }
                posUnix = posUnix == -1 ? posWin : posUnix;
                posWin = posWin == -1 ? posUnix : posWin;
                return Math.min(posUnix, posWin) + 1;
            }
            char ch1 = filename.charAt(1);
            if (ch1 == ':') {
                ch0 = Character.toUpperCase(ch0);
                if (ch0 >= 'A' && ch0 <= 'Z') {
                    if (len == 2 || isSeparator(filename.charAt(2)) == false) {
                        return 2;
                    }
                    return 3;
                }
                return -1;

            } else if (isSeparator(ch0) && isSeparator(ch1)) {
                int posUnix = filename.indexOf(UNIX_SEPARATOR, 2);
                int posWin = filename.indexOf(WINDOWS_SEPARATOR, 2);
                if (posUnix == -1 && posWin == -1 || posUnix == 2 || posWin == 2) {
                    return -1;
                }
                posUnix = posUnix == -1 ? posWin : posUnix;
                posWin = posWin == -1 ? posUnix : posWin;
                return Math.min(posUnix, posWin) + 1;
            } else {
                return isSeparator(ch0) ? 1 : 0;
            }
        }
    }

    /**
     * Returns the index of the last directory separator character.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * The position of the last forward or backslash is returned.
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param filename the filename to find the last path separator in, null returns -1
     * @return the index of the last separator character, or -1 if there
     * is no such character
     */
    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    /**
     * Returns the index of the last extension separator character, which is a dot.
     * <p>
     * This method also checks that there is no directory separator after the last dot.
     * To do this it uses {@link #indexOfLastSeparator(String)} which will
     * handle a file in either Unix or Windows format.
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param filename the filename to find the last path separator in, null returns -1
     * @return the index of the last separator character, or -1 if there
     * is no such character
     */
    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
        int lastSeparator = indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    //-----------------------------------------------------------------------

    public static String getPrefix(String filename) {
        if (filename == null) {
            return null;
        }
        int len = getPrefixLength(filename);
        if (len < 0) {
            return null;
        }
        if (len > filename.length()) {
            return filename + UNIX_SEPARATOR;  // we know this only happens for unix
        }
        return filename.substring(0, len);
    }


    public static String getPath(String filename) {
        return doGetPath(filename, 1);
    }


    public static String getPathNoEndSeparator(String filename) {
        return doGetPath(filename, 0);
    }

    /**
     * Does the work of getting the path.
     *
     * @param filename     the filename
     * @param separatorAdd 0 to omit the end separator, 1 to return it
     * @return the path
     */
    private static String doGetPath(String filename, int separatorAdd) {
        if (filename == null) {
            return null;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        int endIndex = index + separatorAdd;
        if (prefix >= filename.length() || index < 0 || prefix >= endIndex) {
            return "";
        }
        return filename.substring(prefix, endIndex);
    }


    public static String getFullPath(String filename) {
        return doGetFullPath(filename, true);
    }


    public static String getFullPathNoEndSeparator(String filename) {
        return doGetFullPath(filename, false);
    }

    /**
     * Does the work of getting the path.
     *
     * @param filename         the filename
     * @param includeSeparator true to include the end separator
     * @return the path
     */
    private static String doGetFullPath(String filename, boolean includeSeparator) {
        if (filename == null) {
            return null;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        if (prefix >= filename.length()) {
            if (includeSeparator) {
                return getPrefix(filename);  // add end slash if necessary
            } else {
                return filename;
            }
        }
        int index = indexOfLastSeparator(filename);
        if (index < 0) {
            return filename.substring(0, prefix);
        }
        int end = index + (includeSeparator ? 1 : 0);
        if (end == 0) {
            end++;
        }
        return filename.substring(0, end);
    }


    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }


    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Checks whether the extension of the filename is that specified.
     * <p>
     * This method obtains the extension as the textual part of the filename
     * after the last dot. There must be no directory separator after the dot.
     * The extension check is case-sensitive on all platforms.
     *
     * @param filename  the filename to query, null returns false
     * @param extension the extension to check for, null or empty checks for no extension
     * @return true if the filename has the specified extension
     */
    public static boolean isExtension(String filename, String extension) {
        if (filename == null) {
            return false;
        }
        if (extension == null || extension.length() == 0) {
            return indexOfExtension(filename) == -1;
        }
        String fileExt = getExtension(filename);
        return fileExt.equals(extension);
    }

    /**
     * Checks whether the extension of the filename is one of those specified.
     * <p>
     * This method obtains the extension as the textual part of the filename
     * after the last dot. There must be no directory separator after the dot.
     * The extension check is case-sensitive on all platforms.
     *
     * @param filename   the filename to query, null returns false
     * @param extensions the extensions to check for, null checks for no extension
     * @return true if the filename is one of the extensions
     */
    public static boolean isExtension(String filename, String[] extensions) {
        if (filename == null) {
            return false;
        }
        if (extensions == null || extensions.length == 0) {
            return indexOfExtension(filename) == -1;
        }
        String fileExt = getExtension(filename);
        for (String extension : extensions) {
            if (fileExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the extension of the filename is one of those specified.
     * <p>
     * This method obtains the extension as the textual part of the filename
     * after the last dot. There must be no directory separator after the dot.
     * The extension check is case-sensitive on all platforms.
     *
     * @param filename   the filename to query, null returns false
     * @param extensions the extensions to check for, null checks for no extension
     * @return true if the filename is one of the extensions
     */
    public static boolean isExtension(String filename, Collection<String> extensions) {
        if (filename == null) {
            return false;
        }
        if (extensions == null || extensions.isEmpty()) {
            return indexOfExtension(filename) == -1;
        }
        String fileExt = getExtension(filename);
        for (String extension : extensions) {
            if (fileExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    //-----------------------------------------------------------------------

    /**
     * Splits a string into a number of tokens.
     * The text is split by '?' and '*'.
     * Where multiple '*' occur consecutively they are collapsed into a single '*'.
     *
     * @param text the text to split
     * @return the array of tokens, never null
     */
    static String[] splitOnTokens(String text) {
        // used by wildcardMatch
        // package level so a unit test may run on this

        if (text.indexOf('?') == -1 && text.indexOf('*') == -1) {
            return new String[]{text};
        }

        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '?' || array[i] == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (array[i] == '?') {
                    list.add("?");
                } else if (list.isEmpty() ||
                        i > 0 && list.get(list.size() - 1).equals("*") == false) {
                    list.add("*");
                }
            } else {
                buffer.append(array[i]);
            }
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }

        return list.toArray(new String[list.size()]);
    }

}
