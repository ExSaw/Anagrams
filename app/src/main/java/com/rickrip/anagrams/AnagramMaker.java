package com.rickrip.anagrams;

public class AnagramMaker {

    public String getAnagram(String inputTextStr, String filterStr) {

        if(inputTextStr==null||filterStr==null) return "";

        String wordStr = "", resultStr = "";

        for (int i = 0; i < inputTextStr.length(); i++) { // making separate words

            if (inputTextStr.charAt(i) != ' ') {
                wordStr += inputTextStr.charAt(i);
            }

            if ((inputTextStr.charAt(i) == ' ' || i >= inputTextStr.length() - 1)
                    && !wordStr.equals("")) { // one word created, proceed this word

                char[] inputCharArray = wordStr.toCharArray();
                wordStr = "";
                char[] outputCharArray = new char[inputCharArray.length];

                for (int j = 0, s = inputCharArray.length - 1; j < inputCharArray.length; j++) {

                    if (filterStr.contains(String.valueOf(inputCharArray[j]))) { // if char in filter
                        outputCharArray[j] = inputCharArray[j];
                    } else {
                        for (int p = s; p >= 0; p--) {
                            if (!filterStr.contains(String.valueOf(inputCharArray[p]))) { // if char isn't in filter
                                outputCharArray[j] = inputCharArray[p];
                                s = p - 1;
                                break;
                            }
                        }
                    }
                }
                resultStr += String.valueOf(outputCharArray) + " ";
            }
        }

        return resultStr;
    }

}
