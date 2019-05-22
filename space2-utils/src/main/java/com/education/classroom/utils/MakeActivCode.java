package com.education.classroom.utils;
    import java.util.UUID;
      
    /**
     * 生成激活码
     * @Class Name MakeActivCode
     * @author 路志友
     * @Create In 2016年7月13日
     */
    public class MakeActivCode {  
    	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    	
    	public static String generateShortUuid() {
    		StringBuffer shortBuffer = new StringBuffer();
    		String uuid = UUID.randomUUID().toString().replace("-", "");
    		for (int i = 0; i < 8; i++) {
    		    String str = uuid.substring(i * 4, i * 4 + 4);
    		    int x = Integer.parseInt(str, 16);
    		    shortBuffer.append(chars[x % 0x3E]);
    		}
    		return shortBuffer.toString();

    		}
    	
        public static void main(String[] args) {  
            // 测试多线程调用订单号生成工具  
            try {  
                for (int i = 0; i < 500; i++) {  
                    Thread t1 = new Thread(new Runnable() {  
                        public void run() {  
                        	
                        	System.out.println(generateShortUuid().toUpperCase());
                        }  
                    }, "at" + i);  
                    t1.start();  
      
                    Thread t2 = new Thread(new Runnable() {  
                        @SuppressWarnings("static-access")
						public void run() {  
                        	MakeActivCode makeOrder = new MakeActivCode();  
                            System.out.println(makeOrder.generateShortUuid().toUpperCase());
                        }  
                    }, "bt" + i);  
                    t2.start();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
      
        }  
      
    }  