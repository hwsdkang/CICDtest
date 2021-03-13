//FED4 FSN Sample

import com.fasoo.adk.packager.WorkPackager;

public class WorkPackager_FSN {

	public static void main(String[] args) {
		//��ȣȭ
		(new WorkPackager_FSN()).DoPackagingFSN();
		
		//��ȣȭ
		(new WorkPackager_FSN()).DoExtract();
	}

	public void DoPackagingFSN(){
		System.out.println("Packaging() ======================================");
        int nRet = 0;
        boolean bRet = false;
		String HOMEDIR = "./fsdinit";        //fsdinit ���丮 ���
		String SERVERID = "0000000000001323";                 //DSDCODE
		String source = "./Packager/content/�׽�Ʈ.docx";      //
		String target = "./Packager/content/enc_�׽�Ʈ.docx";
        String orgFilename ="enc_�׽�Ʈ.docx";

        WorkPackager oWorkPackager = new WorkPackager();
        
		int nFileType = oWorkPackager.GetFileType(source);

		if (nFileType == 29)
		{
			if( oWorkPackager.IsSupportFile(HOMEDIR, SERVERID, source) ){

			boolean bret = oWorkPackager.DoPackagingFsn2(HOMEDIR, //���翡 ������ ���� �� fsdinit ������ ���� ���
									SERVERID, //���翡 ����� Contents Provider ID
									source, //��ȣȭ ��� ������ ������ + ���ϸ�
									target, //��ȣȭ �� ������ ������+���ϸ�
									orgFilename, //���� ���ϸ�(�α׿� ��ϵǴ� ���ϸ�)
									"admin", // ���� ������ ID
									"������", // ���� �����ڸ�
									"test", "�ƹ���", "001", "��_1", //������ ���̵�, ������ �̸�, ������ �μ��ڵ�, ������ �μ���
									"test", "�ƹ���", "001", "��_1",	//������ ���̵�, ������ �̸�, ������ �μ��ڵ�, ������ �μ���
									"1"); //SecureLevel Code
			
				if (!bret) {
					System.out.println("��ȣȭ �� ������ �����Դϴ�.");
					System.out.println(" ���� ����..");
					System.out.println("    ["+ oWorkPackager.getLastErrorNum()+"] "+oWorkPackager.getLastErrorStr());
					
				} else {
					System.out.println("���� �߶�... " + oWorkPackager.getContainerFileName());
				}
			}
		}

	}
	
	public void DoExtract(){
		System.out.println("Extract ======================================");
        int nRet = 0;
        boolean bRet = false;
		String HOMEDIR = "./fsdinit";        //fsdinit ���
		String FSDINIT = "0000000000001323";                  //DSDCODE
		String srcFile = "./Packager/content/enc_�׽�Ʈ.docx"; //��ȣȭ������
		String tarFile = "./Packager/content/dec_�׽�Ʈ.docx"; //��ȣȭ������
        
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
				System.out.println("��ȣȭ ����!!" + oWorkPackager.getContainerFileName());
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
