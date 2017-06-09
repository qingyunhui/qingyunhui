package qing.yun.hui.common.utils.sftp;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月9日下午3:55:02
 **/
@Getter
public enum SFTPEnum implements ICommonEnum{
	
	OVERWRITE(0,"覆盖模式，如果目标文件存在，则会覆盖目标文件、产生新的文件."),
	RESUME(1,"断点续传模式，如果已经上传一部分、由于网络中断，则下一次上传时相同文件时，则会从上一次中断的地方续传."),
	APPEND(2,"追加模式，如果目标文件已经存在，传输的文件将在目标文件后追加.");
	private final int value;
    private final String name;
    
    private SFTPEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

	public String getCode() {
		return String.valueOf(value);
	}

}
