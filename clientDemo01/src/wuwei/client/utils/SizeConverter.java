package wuwei.client.utils;

public enum  SizeConverter {
	 /** ת�����ⵥλ�Ĵ�С, ���ؽ���������λС������������λ. */  
    Arbitrary {  
        @Override  
        public String convert(float size) {  
            while (size > 1024) {  
                size /= 1024;  
            }  
            return String.format(FORMAT_F, size);  
        }  
    },  
      
    // -----------------------------------------------------------------------  
    // �е�λ  
    /** ת����λΪB�Ĵ�С, ���ؽ���������λС���Լ���λ. ��: 1024B->1KB, (1024*1024)B->1MB */  
    B {  
        @Override  
        public String convert(float B) {  
            return converter(0, B);  
        }  
    },  
    /** ת����λΪB�Ĵ�С, ���ؽ���������λС���Լ���λ. */  
    KB {  
        @Override  
        public String convert(float KB) {  
            return converter(1, KB);  
        }  
    },  
    /** ת����λΪMB�Ĵ�С, ���ؽ���������λС���Լ���λ. */  
    MB {  
        @Override  
        public String convert(float MB) {  
            return converter(2, MB);  
        }  
    },  
    /** ת����λΪGB�Ĵ�С, ���ؽ���������λС���Լ���λ. */  
    GB {  
        @Override  
        public String convert(float GB) {  
            return converter(3, GB);  
        }  
    },  
    /** ת����λΪTB�Ĵ�С, ���ؽ���������λС���Լ���λ. */  
    TB {  
        @Override  
        public String convert(float TB) {  
            return converter(4, TB);  
        }  
    },  
      
    // -----------------------------------------------------------------------  
    // trimû��λ  
    /** ת�����ⵥλ�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, ��������λ. */  
    ArbitraryTrim {  
        @Override  
        public String convert(float size) {  
            while (size > 1024) {  
                size /= 1024;  
            }  
  
            int sizeInt = (int) size;  
            boolean isfloat = size - sizeInt > 0.0F;  
            if (isfloat) {  
                return String.format(FORMAT_F, size);  
            }  
            return String.format(FORMAT_D, sizeInt);  
        }  
    },  
      
    // -----------------------------------------------------------------------  
    // trim�е�λ  
    /** ת����λΪB�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, �������λ. */  
    BTrim {  
        @Override  
        public String convert(float B) {  
            return trimConverter(0, B);  
        }  
    },  
    /** ת����λΪKB�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, �������λ. */  
    KBTrim {  
        @Override  
        public String convert(float KB) {  
            return trimConverter(1, KB);  
        }  
    },  
    /** ת����λΪMB�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, �������λ. */  
    MBTrim {  
        @Override  
        public String convert(float MB) {  
            return trimConverter(2, MB);  
        }  
    },  
    /** ת����λΪGB�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, �������λ. */  
    GBTrim {  
        @Override  
        public String convert(float GB) {  
            return trimConverter(3, GB);  
        }  
    },  
    /** ת����λΪTB�Ĵ�С, ���ؽ��С������Ϊ0ʱ��ȥ����λС��, �������λ. */  
    TBTrim {  
        @Override  
        public String convert(float TB) {  
            return trimConverter(4, TB);  
        }  
    };  
    /*** 
     * <p> ��ָ���Ĵ�Сת����1024��Χ�ڵĴ�С. ע��÷��������λΪPB, ��С��λΪB,  
     * �κγ����÷�Χ�ĵ�λ���ջ���ʾΪ**. </p> 
     *  
     * @param size Ҫת���Ĵ�С, ע���Ǹ�����, ��Ҫ�����εķ�ʽ����, ����������. 
     *         (��: 1024*1024*1024*1024*1024�����, ʹ���Ϊ0, ��Ϊ���Ƚ������int��˺���ת��Ϊfloat;  
     *         ��1024.0F*1024.0F*1024.0F*1024.0F*1024.0F�Ͳ������) 
     * @return 
     */  
    abstract public String convert(float size);  
      
    // -----------------------------------------------------------------------  
    // ��λת��  
      
    private static final String[] UNITS = new String[] {  
        "B", "KB", "MB", "GB", "TB", "PB", "**"  
    };  
      
    private static final int LAST_IDX = UNITS.length-1;  
      
    private static final String FORMAT_F = "%1$-1.2f";  
    private static final String FORMAT_F_UNIT = "%1$-1.2f%2$s";  
      
    private static final String FORMAT_D = "%1$-1d";  
    private static final String FORMAT_D_UNIT = "%1$-1d%2$s";  
      
    // -----------------------------------------------------------------------  
    private static String converter(int unit, float size) {  
        int unitIdx = unit;  
        while (size > 1024) {  
            unitIdx++;  
            size /= 1024;  
        }  
        int idx = unitIdx < LAST_IDX ? unitIdx : LAST_IDX;  
        return String.format(FORMAT_F_UNIT, size, UNITS[idx]);  
    }  
      
    private static String trimConverter(int unit, float size) {  
        int unitIdx = unit;  
        while (size > 1024) {  
            unitIdx++;  
            size /= 1024;  
        }  
  
        int sizeInt = (int) size;  
        boolean isfloat = size - sizeInt > 0.0F;  
        int idx = unitIdx < LAST_IDX ? unitIdx : LAST_IDX;  
        if (isfloat) {  
            return String.format(FORMAT_F_UNIT, size, UNITS[idx]);  
        }  
        return String.format(FORMAT_D_UNIT, sizeInt, UNITS[idx]);  
    }  
      
    // -----------------------------------------------------------------------  
    public static String convertBytes(float B, boolean trim) {  
        return trim ? trimConvert(0, B, true) : convert(0, B, true);  
    }  
      
    public static String convertKB(float KB, boolean trim) {  
        return trim ? trimConvert(1, KB, true) : convert(1, KB, true);  
    }  
      
    public static String convertMB(float MB, boolean trim) {  
        return trim ? trimConvert(2, MB, true) : convert(2, MB, true);  
    }  
      
    /*** 
     * <p> �洢��С��λ���ת��. ע��÷��������λΪPB, ��С��λΪB,  
     * �κγ����÷�Χ�ĵ�λ���ջ���ʾΪ**. </p> 
     *  
     * @param unit ���ĸ���λ��ʼ 
     * @param size �洢��С, ע����float, ��Ҫ�����ε���ʽ����, ��������(��:1024*1024����, 
     * �����Ƚ�1024*1024��Ϊint�����ת��Ϊfloat��, ���ֵ����Ļ��ͻ������,  
     * ������ôд1024.0F*1024.0F) 
     * @param withUnit ���صĽ���ַ����Ƿ���ж�Ӧ�ĵ�λ 
     * @return 
     */  
    private static String convert(int unit, float size, boolean withUnit) {  
        int unitIdx = unit;  
        while (size > 1024) {  
            unitIdx++;  
            size /= 1024;  
        }  
        if (withUnit) {  
            int idx = unitIdx < LAST_IDX ? unitIdx : LAST_IDX;  
            return String.format(FORMAT_F_UNIT, size, UNITS[idx]);  
        }  
        return String.format(FORMAT_F, size);  
    }  
      
    /*** 
     * <p> �洢��С��λ���ת��, ���ת����С������Ϊ0, ��ȥ��С������.  
     * ע��÷��������λΪPB, ��С��λΪB, �κγ����÷�Χ�ĵ�λ���ջ���ʾΪ**. </p> 
     *  
     * @param unit ���ĸ���λ��ʼ 
     * @param size �洢��С, ע����float, ��Ҫ�����ε���ʽ����, ��������(��:1024*1024����, 
     * �����Ƚ�1024*1024��Ϊint�����ת��Ϊfloat��, ���ֵ����Ļ��ͻ������,  
     * ������ôд1024.0F*1024.0F) 
     * @param withUnit ���صĽ���ַ����Ƿ���ж�Ӧ�ĵ�λ 
     * @return 
     */  
    private static String trimConvert(int unit, float size, boolean withUnit) {  
        int unitIdx = unit;  
        while (size > 1024) {  
            unitIdx++;  
            size /= 1024;  
        }  
  
        int sizeInt = (int) size;  
        boolean isfloat = size - sizeInt > 0.0F;  
        if (withUnit) {  
            int idx = unitIdx < LAST_IDX ? unitIdx : LAST_IDX;  
            if (isfloat) {  
                return String.format(FORMAT_F_UNIT, size, UNITS[idx]);  
            }  
            return String.format(FORMAT_D_UNIT, sizeInt, UNITS[idx]);  
        }  
  
        if (isfloat) {  
            return String.format(FORMAT_F, size);  
        }  
        return String.format(FORMAT_D, sizeInt);  
    } 
}
