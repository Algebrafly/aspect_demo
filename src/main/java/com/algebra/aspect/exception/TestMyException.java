package com.algebra.aspect.exception;

/**
 * @author al
 * @date 2019/12/11 9:14
 * @description
 */
public class TestMyException {

    public static void main(String[] args) {
        String name="";
        int i=0;
        try {
            if (name == null) {
                throw new BusinessException(ErrorCodeEnum.ILLEGAL_ARGS);
            }
            if(i==0) {
                throw new BusinessException(ErrorCodeEnum.ILLEGAL_ARGS, "参数不能为0");
            }
        }catch (BusinessException e){
            e.printStackTrace();
            System.out.println("异常码："+e.getErrorCode().getCode());
            System.out.println("异常描述："+e.getMessage());
        }

    }
}
