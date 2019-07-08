import org.junit.Test;
import com.sample.SampleEjb;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SampleEjbTest {

    @Test
    public void add() throws Exception {
        SampleEjb sample = new SampleEjb();
        assertThat(sample.add(1, 2), is(3));
    }

}