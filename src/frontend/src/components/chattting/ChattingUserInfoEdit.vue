<template>
    <v-container class="fill-height" fluid v-if="!this.join">
        <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="4">
                <v-card class="elevation-12">
                    <v-toolbar color="primary" dark flat>
                        <v-toolbar-title>채팅방 입장하기</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                    label="이름"
                                    prepend-icon="mdi-account"
                                    type="text"
                                    v-model="userName"
                                    @keydown="joinChattingRoomByEnter"
                            ></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="joinChattingRoom">입장</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
    <ChattingRoom :isAdmin="false" :userName="this.userName" v-else></ChattingRoom>
</template>

<script>
    import ChattingRoom from "./ChattingRoom";

    export default {
        name: "ChattingUserInfoEdit",
        components: {ChattingRoom},
        data() {
            return {
                join: false,
                userName: "",
            }
        },
        methods: {
            joinChattingRoomByEnter(event) {
                if (event.keyCode === 13) {
                    this.joinChattingRoom()
                }
            },
            joinChattingRoom() {
                if (this.userName === '' || this.userName.length === 0 && this.userName === '\n' && this.userName === ' ') return;
                this.join = true;
            }
        }
    }
</script>

<style scoped>

</style>