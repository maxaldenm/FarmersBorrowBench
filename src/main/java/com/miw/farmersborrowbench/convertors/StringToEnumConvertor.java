package com.miw.farmersborrowbench.convertors;


import com.miw.farmersborrowbench.beans.Sector;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConvertor implements Converter<String, Sector> {

    @Override
    public Sector convert(String s) {
        if(s.equals("Weed")) return Sector.WEED;
        if(s.equals("XTC")) return Sector.XTC;
        if(s.equals("Meth")) return Sector.METH;
        if(s.equals("Adult")) return Sector.ADULT;
        return Sector.CRYPTO;
    }
}
