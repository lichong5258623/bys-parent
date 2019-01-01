package com.chong.bys.bystest.poi.test;

import com.chong.bys.eunm.MsgActionEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author lichong
 * 2018/10/23 16:02
 * @version 1
 * @since 1.0
 */
@Slf4j
public class ExcelTest {


    @Test
    public void testReadExcel() throws IOException {

        File file = new File("D:\\work\\workspace\\ideaworkspace01\\bys-parent\\bys-test\\偏远地区邮编表模板.xlsx");
        List<String[]> strings = POIUtil.readExcel(file,0,2);
        for (String[] string : strings) {
            List<String> list = Arrays.asList(string);
            String join = String.join("", list);
            System.out.println(join);
        }


        Integer type = MsgActionEnum.CHAT.getType();

    }
}
