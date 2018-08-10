/**
 * 
 */
package framework.objects;

/**
* Date : 2017-03-28
* Owner : lixia.yuan
* @author lixia.yuan
*/
public class Property {

	private String SrvID;
	
	private long WaitPageTimeLOW;
	private long WaitPageTimeMEDIUM;
	private long WaitPageTimeHIGH;
	private long OperationTimeOut;
	
	private long shortSleepTime;
	private long mediumSleepTime;	
	private long longSleepTime;	
	
	private String localLogRepository;
	private String remoteLogRepository;
	
	private String localLogSrvHost;
	private String localLogSrvIP;
	private String localLogUserName;
	private String localLogPassword;
	
	private String normalFileDir;
	private String bigFileDir;
	private String folderFileDir;
	
	private String remoteBuiFile;
	private String remoteCommFile;
	private String localDir;
	
	private String jdbcDriver;
	private String jdbcUserName;
	private String jdbcPasswd;
	private String jdbcUrl;
	
	private String emailServerHost;
	private long emailServerPort;
	private String emailAddress;
	private String emailPassword;	
	
	private String reportLinkPrefix;

	private String testCasesListCsvFilePath;

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPasswd() {
		return jdbcPasswd;
	}

	public void setJdbcPasswd(String jdbcPasswd) {
		this.jdbcPasswd = jdbcPasswd;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public void setSrvID(String SrvID) {
        this.SrvID = SrvID;      
    }
    
	
    public String getSrvID() {
        return this.SrvID;
    }   	

    public void setWaitPageTimeLOW(long WaitPageTimeLOW) {
        this.WaitPageTimeLOW = WaitPageTimeLOW;             
    }
    
    public long getWaitPageTimeLOW() {
        return this.WaitPageTimeLOW;
    }   
	
    public void setWaitPageTimeMEDIUM(long WaitPageTimeMEDIUM) {
        this.WaitPageTimeMEDIUM = WaitPageTimeMEDIUM;             
    }
    
    public long getWaitPageTimeMEDIUM() {
        return this.WaitPageTimeMEDIUM;
    }   
	
    public void setWaitPageTimeHIGH(long WaitPageTimeHIGH) {
        this.WaitPageTimeHIGH = WaitPageTimeHIGH;             
    }
    
    public long getWaitPageTimeHIGH() {
        return this.WaitPageTimeHIGH;
    }   
	
    public void setOperationTimeOut(long OperationTimeOut) {
        this.OperationTimeOut = OperationTimeOut;             
    }
    
    public long getOperationTimeOut() {
        return this.OperationTimeOut;
    }   	
	
    public void setShortSleepTime(long shortSleepTime) {
        this.shortSleepTime = shortSleepTime;             
	}
	    
	public long getShortSleepTime() {
	    return this.shortSleepTime;
	}   
	    
	public void setMediumSleepTime(long mediumSleepTime) {
        this.mediumSleepTime = mediumSleepTime;             
    }
    
    public long getMediumSleepTime() {
        return this.mediumSleepTime;
    }   
	
    public void setLongSleepTime(long longSleepTime) {
        this.longSleepTime = longSleepTime;             
    }
    
    public long getlongSleepTime() {
        return this.longSleepTime;
    }   	
    
    public void setLocalLogRepository(String localLogRepository) {
        this.localLogRepository = localLogRepository;      
    }
    
    public String getLocalLogRepository() {
        return this.localLogRepository;
    }   
    
    public void setRemoteLogRepository(String remoteLogRepository) {
        this.remoteLogRepository = remoteLogRepository;      
    }
    
    public String getRemoteLogRepository() {
        return this.remoteLogRepository;
    }   
    
    public void setLocalLogSrvHost(String srvHost) {
        this.localLogSrvHost = srvHost;      
    }
    
    public String getLocalLogSrvHost() {
        return this.localLogSrvHost;
    } 
    
    public void setLocalLogSrvIP(String srvIP) {
        this.localLogSrvIP = srvIP;      
    }
    
    public String getLocalLogSrvIP() {
        return this.localLogSrvIP;
    }   
    
    public void setLocalLogUserName(String logUserName) {
        this.localLogUserName = logUserName;      
    }
    
    public String getLocalLogUserName() {
        return this.localLogUserName;
    }   
    
    public void setLocalLogPassword(String localLogPassword) {
        this.localLogPassword = localLogPassword;      
    }
    
    public String getLocalLogPassword() {
        return this.localLogPassword;
    }   
    
    public String getNormalFileDir() {
		return normalFileDir;
	}

	public void setNormalFileDir(String normalFileDir) {
		this.normalFileDir = normalFileDir;
	}

	public String getBigFileDir() {
		return bigFileDir;
	}

	public void setBigFileDir(String bigFileDir) {
		this.bigFileDir = bigFileDir;
	}

	public String getFolderFileDir() {
		return folderFileDir;
	}

	public void setFolderFileDir(String folderFileDir) {
		this.folderFileDir = folderFileDir;
	}
    

	public String getLocalDir() {
		return localDir;
	}
	
	public void setLocalDir(String localFile) {
		this.localDir = localFile;
	}
	public String getRemoteBuiFile() {
		return remoteBuiFile;
	}

	public void setRemoteBuiFile(String remoteBuiFile) {
		this.remoteBuiFile = remoteBuiFile;
	}

	public String getRemoteCommFile() {
		return remoteCommFile;
	}

	public void setRemoteCommFile(String remoteCommFile) {
		this.remoteCommFile = remoteCommFile;
	}
	
	
	public void setEmailServerHost(String emailServerHost){
		this.emailServerHost = emailServerHost;
	}
	
	public String getEmailServerHost(){
		return this.emailServerHost;
	}
	
	public void setEmailServerPort(long emailServerPort){
		this.emailServerPort = emailServerPort;
	}
	
	public long getEmailServerPort(){
		return this.emailServerPort;
	}
	
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress(){
		return this.emailAddress;
	}
	
	public void setEmailPassword(String emailPassword){
		this.emailPassword = emailPassword;
	}
	
	public String getEmailPassword(){
		return this.emailPassword;
	}
	
	public String getReportLinkPrefix() {
		return this.reportLinkPrefix;
	}

	public void setReportLinkPrefix(String reportLinkPrefix) {
		this.reportLinkPrefix = reportLinkPrefix;				
	}
	
	public String getTestCasesListCsvFilePath() {
		return this.testCasesListCsvFilePath;
	}
	
	public void setTestCasesListCsvFilePath(String testCasesListCsvFilePath) {
		this.testCasesListCsvFilePath = testCasesListCsvFilePath;				
	}
	
	
}
