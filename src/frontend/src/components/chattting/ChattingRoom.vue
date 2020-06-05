<template>
    <v-card
            class="mx-auto"
            width="60%"
            height="80%"
            max-height="650px"
            color="#FDFDFD"
            style="margin-top: 3%"
    >
        <v-card-text>
            <div>{{ this.userName }}님 안녕하세요.</div>
        </v-card-text>
        <v-card-text id="chat-room" style="max-height: 70%; overflow: scroll">
            <v-col
                    v-for="(item, idx) in messages"
                    :key="idx"
                    cols="12"
            >
                <v-card color="rgba(247, 230, 0, 1)" v-if="item.admin" style="text-align: center">
                    <v-card-subtitle>
                        <span>관리자 : </span>
                        <span v-text="item.content"></span>
                    </v-card-subtitle>
                </v-card>
                <v-card color="rgba(0, 0, 0, 0.1)" v-else-if="messages.length - 1 === idx">
                    <v-card-subtitle>
                        <span v-text="item.userName + ' : '"></span>
                        <span v-text="item.content"></span>
                    </v-card-subtitle>
                </v-card>
                <v-card color="white" v-else>
                    <v-card-subtitle>
                        <span v-text="item.userName + ' : '"></span>
                        <span v-text="item.content"></span>
                    </v-card-subtitle>
                </v-card>
            </v-col>
        </v-card-text>
        <v-row justify="center" style="margin-left: 30px; margin-right: 30px; margin-top: 30px">
            <v-col style="padding-right: 25px; padding-left: 25px">
                <v-row>
                        <v-text-field
                                v-model="message"
                                @keyup="sendMessage"
                                label="입력해주세요."
                                hint="고운말을 사용합시다^^"
                                outlined
                        ></v-text-field>
                </v-row>
            </v-col>
        </v-row>
    </v-card>
</template>

<script>
    import Stomp from 'webstomp-client'
    import SockJS from 'sockjs-client'
    import { BASE_URL } from '../../utils/api'

    export default {
        name: "ChattingRoom",
        props: ['isAdmin', 'userName'],
        created() {
            this.connect()
        },
        updated() {
            const container = this.$el.querySelector("#chat-room");
            container.scrollTop = container.scrollHeight;
        },
        data: () => ({
            isAdmin: false,
            userName: "",
            message: "",
            messages: [],
        }),
        methods: {
            sendMessage (e) {
                if(e.keyCode === 13 && this.userName !== '' && this.message !== '' && this.message !== '\n'){
                    this.send();
                    this.message = ''
                }
            },
            send() {
                if (this.stompClient && this.stompClient.connected) {
                    const message = {
                        admin: this.isAdmin,
                        userName: this.userName,
                        content: this.message
                    };
                    this.stompClient.send("/api/chat/receive", JSON.stringify(message), {});
                }
            },
            connect() {
                let socket = new SockJS(`${BASE_URL}/websocket`);
                this.stompClient = Stomp.over(socket);
                this.stompClient.connect(
                    {},
                    () => {
                        this.connected = true;
                        this.stompClient.subscribe("/api/chat/send", res => {
                            this.messages.push(JSON.parse(res.body))
                        });
                    },
                    () => {
                        this.connected = false;
                    }
                );
            }
        }
    }
</script>

<style scoped>
</style>