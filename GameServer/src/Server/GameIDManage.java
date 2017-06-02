package Server;

public class GameIDManage {

    public static final int homeNumber = 4;
    public static final int setNumber = 4;
    private int[][] gameID = new int[homeNumber + 1][setNumber + 1];
    private final int clientMaxNum = 1000;
    private int[] clientIdList = new int[clientMaxNum];

    public int getHomeNumber() {
        return homeNumber;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public int getClientMaxNum() {
        return clientMaxNum;
    }

    public GameIDManage() {
    }

    public void displayIdList() {
        for (int i = 0; i < 10; i++) {
            System.out.print(clientIdList[i] + " ");
        }
        System.out.println();
    }

    public void displayGameId() {
        for (int i = 0; i <= homeNumber; i++) {
            for (int j = 0; j <= setNumber; j++) {
                System.out.print(gameID[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void nameListNorms(int flag) {
        if (flag == 0) {
            for (int i = 0; i <= clientMaxNum; i++) {
                clientIdList[i] = 0;
            }
        } else {
            int count = 0;
            for (int i = 1; i <= clientMaxNum; i++) {
                if (clientIdList[i] != 0) {
                    count++;
                }
            }
            clientIdList[0] = count;
        }
    }

    public void gameIDNorms(int flag) {
        if (flag == 0) {
            for (int i = 0; i <= homeNumber; i++) {
                for (int j = 0; j <= setNumber; j++) {
                    gameID[i][j] = 0;
                }
            }
        } else {
            for (int i = 1; i <= homeNumber; i++) {
                int count = 0;
                for (int j = 1; j <= setNumber; j++) {
                    if (gameID[i][j] != 0) {
                        count++;
                    }
                }
                gameID[i][0] = count;
            }
        }
    }
    public int getIdNumber(){
        return clientIdList[0];
    }
    
    public int getGameIDNumber(int homeID){
        return gameID[homeID][0];
    }
    
    public int getClientId() {
        if (clientIdList[0] >= clientMaxNum) {
            return 0;
        }
        for (int i = 1; i < clientMaxNum; i++) {
            if (clientIdList[i] == 0) {
                clientIdList[0]++;
                clientIdList[i]=1;
                return i;
            }
        }
        return 0;
    }

    public int getGameID(int homeID) {
        if (homeID == 0) {
            return 0;
        } else {
            int i;
            for (i = 1; i <= setNumber; i++) {
                if (gameID[homeID][i] == 0) {
                    gameID[homeID][i] = 1;
                    gameID[homeID][0]++;
                    return i;
                }
            }
            if (i == setNumber+1) {
                return 0;
            }
        }
        return 0;
    }

    public boolean removeGameID(int home, int set) {
        if (gameID[home][set] == 0) {
            return false;
        }
        gameID[home][set] = 0;
        gameID[home][0]--;
        return true;
    }

    public boolean removeName(int index) {
        if (index > clientMaxNum) {
            return false;
        }
        else if (clientIdList[index] == 0) {
            return false;
        } else {
            clientIdList[index] = 0;
            clientIdList[0]-=1;
            return true;
        }
    }
}
