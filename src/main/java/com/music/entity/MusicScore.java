package com.music.entity;

public class MusicScore {

    private Integer id;

    /**
     * 乐谱名称
     */
    private String musicName;

    /**
     * 考级范围 1:1-3级 2:4-6级 3:7-9级 4:10级 5:演奏
     */
    private Integer musicScope;

    /**
     * 曲子类型  1音阶琶音2练习曲3即兴曲4协奏曲5幻想曲6奏鸣曲7谐谑曲8夜曲9狂想曲10圆舞曲
     *          11组曲（标题音乐）12改编曲13浪漫曲14船歌15室内乐16复调作品17双钢18四手联弹19前奏曲
     */
    private Integer musicType;

    /**
     * 1：左手 2：右手 3、均衡
     */
    private Integer hands;

    /**
     *  曲子特性 1快速跑动2八度3双音4附点5三连音6和弦7断奏8三六度9其他
     */
    private String musicCharacter;

    /**
     * 乐谱时期 1巴洛克 2古典 3浪漫 4印象 5现代
     */
    private Integer musicTime;

    /**
     * 作者知名程度 1:极其知名 2:知名 3:小众
     */
    private Integer authorKnownDegree;

    /**
     * 作者国家 关联表
     */
    private Integer authorCountryId;

    /**
     * 作者名称 关联表
     */
    private Integer authorNameId;

    /**
     * 乐谱保存路径
     */
    private String filePath;

    private String createTime;

    private String uuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Integer getMusicScope() {
        return musicScope;
    }

    public void setMusicScope(Integer musicScope) {
        this.musicScope = musicScope;
    }

    public Integer getMusicType() {
        return musicType;
    }

    public void setMusicType(Integer musicType) {
        this.musicType = musicType;
    }

    public Integer getHands() {
        return hands;
    }

    public void setHands(Integer hands) {
        this.hands = hands;
    }


    public String getMusicCharacter() {
        return musicCharacter;
    }

    public void setMusicCharacter(String musicCharacter) {
        this.musicCharacter = musicCharacter;
    }

    public Integer getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(Integer musicTime) {
        this.musicTime = musicTime;
    }

    public Integer getAuthorKnownDegree() {
        return authorKnownDegree;
    }

    public void setAuthorKnownDegree(Integer authorKnownDegree) {
        this.authorKnownDegree = authorKnownDegree;
    }


    public Integer getAuthorCountryId() {
        return authorCountryId;
    }

    public void setAuthorCountryId(Integer authorCountryId) {
        this.authorCountryId = authorCountryId;
    }

    public Integer getAuthorNameId() {
        return authorNameId;
    }

    public void setAuthorNameId(Integer authorNameId) {
        this.authorNameId = authorNameId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
