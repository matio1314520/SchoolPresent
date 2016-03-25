package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/25.
 */
public class Comment implements Serializable {

    private int code;

    private DataEntity data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {

        private PagingEntity paging;

        private List<CommentsEntity> comments;

        public PagingEntity getPaging() {
            return paging;
        }

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public List<CommentsEntity> getComments() {
            return comments;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
        }

        public static class PagingEntity {
            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class CommentsEntity {
            private String content;

            private int created_at;

            private int id;

            private int item_id;

            private RepliedCommentEntity replied_comment;

            private RepliedUserEntity replied_user;

            private int reply_to_id;

            private boolean show;

            private int status;

            private UserEntity user;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public RepliedCommentEntity getReplied_comment() {
                return replied_comment;
            }

            public void setReplied_comment(RepliedCommentEntity replied_comment) {
                this.replied_comment = replied_comment;
            }

            public RepliedUserEntity getReplied_user() {
                return replied_user;
            }

            public void setReplied_user(RepliedUserEntity replied_user) {
                this.replied_user = replied_user;
            }

            public int getReply_to_id() {
                return reply_to_id;
            }

            public void setReply_to_id(int reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public static class RepliedCommentEntity {

                private String content;

                private int created_at;

                private int id;

                private int item_id;

                private Object reply_to_id;

                private boolean show;

                private int status;

                private int user_id;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public Object getReply_to_id() {
                    return reply_to_id;
                }

                public void setReply_to_id(Object reply_to_id) {
                    this.reply_to_id = reply_to_id;
                }

                public boolean isShow() {
                    return show;
                }

                public void setShow(boolean show) {
                    this.show = show;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }
            }

            public static class RepliedUserEntity {

                private String avatar_url;

                private boolean can_mobile_login;

                private String guest_uuid;

                private int id;

                private String nickname;

                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public String getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(String guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }

            public static class UserEntity {

                private String avatar_url;

                private boolean can_mobile_login;

                private Object guest_uuid;

                private int id;

                private String nickname;

                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }
    }
}
