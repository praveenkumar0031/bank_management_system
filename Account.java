

class Account{
    String acholder="";
    long acno;
    String actype="";
    String acstatus="CLOSED";
    float acbal=0;
    Account(String acholder,String actype,float acbal,int freeno){
        if(isValidAccount(acholder,actype)){
        this.acholder=acholder.toUpperCase();
        this.actype=actype.toUpperCase();
        this.acbal=acbal;
        this.acno=generateAcNO(freeno);
        this.acstatus="ACTIVE";
        System.out.println("--------------------|Account created |-------------------- \n");
        }
    }
    //validate name-- full_alphabet and acc type(savings,current,fd,rd) I/P->(name,acctype) O/P->T/F
    private static boolean isValidAccount(String name,String actype){
        for(char c:name.toCharArray()){
            if(!Character.isAlphabetic(c)){
                return false;
            }
        }
        String type=actype.toUpperCase();
        if(type.equals("SAVINGS")||type.equals("CURRENT")||type.equals("FD")||type.equals("RD")){
        return true;
        }
        else{
            return false;
        }
    }
    //method should have 4 digit no only
    private static long generateAcNO(int lastno){
        return (832000070000l+lastno);
    }

}