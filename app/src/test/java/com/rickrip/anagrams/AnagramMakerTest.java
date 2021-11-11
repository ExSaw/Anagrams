package com.rickrip.anagrams;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

public class AnagramMakerTest {

    private AnagramMaker anagramMaker;
    private final String defaultFilterString = "0123456789`~!@#$%^*()_-+=>|,.>\\\\/?\"':;{[]}№";

    @Before
    public void setup() throws Exception{

       anagramMaker = new AnagramMaker();

       Truth.assertThat(anagramMaker).isNotNull();

    }

    @Test
    public void if_inputString_isNull_return_empty_resultString(){

        String resultStr = anagramMaker.getAnagram(null,"example");

        Truth.assertThat(resultStr).matches("");

    }

    @Test
    public void if_filterString_isNull_return_empty_resultString(){

        String resultStr = anagramMaker.getAnagram("example",null);

        Truth.assertThat(resultStr).matches("");

    }

    @Test
    public void if_inputString_isNull_AND_filterString_isNull_return_empty_resultString(){

        String resultStr = anagramMaker.getAnagram(null,null);

        Truth.assertThat(resultStr).matches("");

    }

    @Test
    public void checkFilterWork(){

        //  a1b2c3d b!@#%aq  wHAt3if   |d1c2b3a q!@#%ab fiTA3Hw |

        String resultStr = anagramMaker.getAnagram("   a1b2c3d b!@#%aq     wHAt3if",defaultFilterString);

        Truth.assertThat(resultStr).matches("d1c2b3a q!@#%ab fitA3Hw ");

    }

}