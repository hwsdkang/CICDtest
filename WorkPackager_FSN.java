//FED4 FSN Sample

import com.fasoo.adk.packager.WorkPackager;

public class WorkPackager_FSN {

	public static void main(String[] args) {
		//암호화
		(new WorkPackager_FSN()).DoPackagingFSN();
		
		//복호화
		(new WorkPackager_FSN()).DoExtract();
	}

	public void DoPackagingFSN(){
		System.out.println("Packaging() ======================================");
        int nRet = 0;
        boolean bRet = false;
		String HOMEDIR = "./fsdinit";        //fsdinit 디렉토리 경로
		String SERVERID = "0000000000001323";                 //DSDCODE
		String source = "./Packager/content/테스트.docx";      //
		String target = "./Packager/content/enc_테스트.docx";
        String orgFilename ="enc_테스트.docx";

        WorkPackager oWorkPackager = new WorkPackager();
        
		int nFileType = oWorkPackager.GetFileType(source);

		if (nFileType == 29)
		{
			if( oWorkPackager.IsSupportFile(HOMEDIR, SERVERID, source) ){

			boolean bret = oWorkPackager.DoPackagingFsn2(HOMEDIR, //고객사에 제공된 파일 중 fsdinit 폴더의 절대 경로
									SERVERID, //고객사에 발행된 Contents Provider ID
									source, //암호화 대상 파일의 절대경로 + 파일명
									target, //암호화 후 생성할 절대경로+파일명
									orgFilename, //원본 파일명(로그에 기록되는 파일명)
									"admin", // 문서 생성자 ID
									"관리자", // 문서 생성자명
									"test", "아무개", "001", "팀_1", //생성자 아이디, 생성자 이름, 생성자 부서코드, 생성자 부서명
									"test", "아무개", "001", "팀_1",	//소유자 아이디, 소유자 이름, 소유자 부서코드, 소유자 부서명
									"1"); //SecureLevel Code
			
				if (!bret) {
					System.out.println("암호화 중 오류중 오류입니다.");
					System.out.println(" 오류 정보..");
					System.out.println("    ["+ oWorkPackager.getLastErrorNum()+"] "+oWorkPackager.getLastErrorStr());
					
				} else {
					System.out.println("성공 했뜸... " + oWorkPackager.getContainerFileName());
				}
			}
		}

	}
	
	public void DoExtract(){
		System.out.println("Extract ======================================");
        int nRet = 0;
        boolean bRet = false;
		String HOMEDIR = "./fsdinit";        //fsdinit 경로
		String FSDINIT = "0000000000001323";                  //DSDCODE
		String srcFile = "./Packager/content/enc_테스트.docx"; //암호화된파일
		String tarFile = "./Packager/content/dec_테스트.docx"; //복호화된파일
        
        WorkPackager oWorkPackager = new WorkPackager();
        oWorkPackager.setOverWriteFlag(true);

		nRet = oWorkPackager.GetFileType(srcFile);

		if (nRet == 103)
		{
			bRet = oWorkPackager.DoExtract(HOMEDIR,
										FSDINIT,
										srcFile,
										tarFile);										 
	        if (!bRet){
				System.out.println("    ["+ oWorkPackager.getLastErrorNum()+"] "+oWorkPackager.getLastErrorStr());
			}else{
				System.out.println("복호화 성공!!" + oWorkPackager.getContainerFileName());
			}
		}

	}

	public boolean IsSupportFile(String srcFile){
		String HOMEDIR = "./fsdinit";
		String FSDINIT = "0000000000001323";

		boolean bRet = false;
		WorkPackager oWorkPackager = new WorkPackager();
        bRet = oWorkPackager.IsSupportFile(HOMEDIR, FSDINIT, srcFile);

		return bRet;
	}
}
