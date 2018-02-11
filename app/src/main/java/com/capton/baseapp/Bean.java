package com.capton.baseapp;

/**
 * Created by capton on 2018/2/8.
 */

public class Bean {


    /**
     * code : 0
     * message : pn=1 type=0
     * data : {'archives': [{'aid': 19183616, 'videos': 24, 'tid': 32, 'tname': '完结动画', 'copyright': 2, 'pic': 'http://i1.hdslb.com/bfs/archive/25547267eaa50382d7873f10509b44447bea2d72.png', 'title': '【720P/合集】风之圣痕【DmzJ字幕组】', 'pubdate': 1517914924, 'ctime': 1517836662, 'desc': '天空在超高层旅馆的上空分成了两半，烧尽一切的烈炎与斩断空气的烈风发生了激烈的冲突。从异界召唤来的精灵兽吼叫着，巨大的魔兽震裂了大地。但是，这并不是发生在异世界的故事，而是围绕着一个和我们相同世界的女子高中生的战斗发生的……', 'state': 0, 'attribute': 16384, 'duration': 34829, 'rights': {'bp': 0, 'elec': 0, 'download': 0, 'movie': 0, 'pay': 0, 'hd5': 0, 'no_reprint': 0}, 'owner': {'mid': 9961250, 'name': '北梦夏栀QAQ', 'face': 'http://i0.hdslb.com/bfs/face/b7bf501ed7a8cbe21cb9e7157373c39b89f4eff2.jpg'}, 'stat': {'aid': 19183616, 'view': 2193, 'danmaku': 42, 'reply': 36, 'favorite': 214, 'coin': 46, 'share': 2, 'now_rank': 0, 'his_rank': 0, 'like': 31}, 'dynamic': ''}], 'page': {'count': 14655, 'num': 1, 'size': 20}}
     */

    private int code;
    private String message;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
