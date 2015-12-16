
package openwebinars.navigationdrawer.models;

public class User {

    private String nickname;
    private String latlon;
    private String id;
    private String sexo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param id
     * @param latlon
     * @param nickname
     * @param sexo
     */
    public User(String nickname, String latlon, String id, String sexo) {
        this.nickname = nickname;
        this.latlon = latlon;
        this.id = id;
        this.sexo = sexo;
    }

    /**
     * 
     * @return
     *     The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @param nickname
     *     The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 
     * @return
     *     The latlon
     */
    public String getLatlon() {
        return latlon;
    }

    /**
     * 
     * @param latlon
     *     The latlon
     */
    public void setLatlon(String latlon) {
        this.latlon = latlon;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * 
     * @param sexo
     *     The sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
