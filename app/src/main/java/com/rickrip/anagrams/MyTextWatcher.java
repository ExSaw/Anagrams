package com.rickrip.anagrams;

import android.text.Editable;
import android.text.TextWatcher;

public class MyTextWatcher implements TextWatcher
{
    private boolean ignoreBool = false; // indicates if the change was made by the TextWatcher itself.
    private String textBeforeStr;
    private String textAfterStr;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        if (ignoreBool) return;

        textBeforeStr = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (ignoreBool) return;

        if(textBeforeStr.length()<s.length()){

            if(s.length()!=0){ // add new char

                char charChar = s.charAt(s.length() - 1);

                    if(!textBeforeStr.contains(String.valueOf(charChar))&& charChar !=' '){
                        textAfterStr = textBeforeStr + charChar;
                    }else{
                        textAfterStr = textBeforeStr;
                    }
            }
        }else{
            textAfterStr = s.toString();
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (ignoreBool) return;

        startUpdate(); // prevent loop

        s.clear();
        s.append(textAfterStr);
        textAfterStr = "";

        endUpdate(); // release
    }

    private void startUpdate(){
        ignoreBool = true;
    }

    private void endUpdate(){
        ignoreBool = false;
    }
}
