<template>
    <v-card
            class="mx-auto"
            width="60%"
            height="80%"
            color="#FDFDFD"
            style="margin-top: 3%"
    >
        <v-card-text>
            <div>관리자 1:1 채팅</div>
        </v-card-text>
        <v-card-text style="max-height: 70%; overflow: scroll">
            <v-col
                    v-for="(item, i) in items"
                    :key="i"
                    cols="12"
            >
                <v-card color="white" style="margin-right: 30%" v-if="isAdmin(item.role)">
                    <v-card-subtitle v-text="item.message"/>
                </v-card>
                <v-card color="#FFD400" class="text-right" style="margin-left: 30%" v-else>
                    <v-card-subtitle v-text="item.message"/>
                </v-card>
            </v-col>
        </v-card-text>
        <v-row justify="center" style="margin-left: 30px; margin-right: 30px; margin-top: 30px">
            <v-col style="padding-right: 25px; padding-left: 25px">
                <v-row>
                    <v-textarea
                            label="관리자에게 메시지 보내기 💬"
                            auto-grow
                            outlined
                            row-height="9"
                            class="mr-3"
                    />
                    <v-btn height="57" color="primary">작성</v-btn>
                </v-row>
            </v-col>
        </v-row>
    </v-card>
</template>

<script>
    export default {
        name: "ChattingRoom",
        data: () => ({
            items: [
                {
                    role: 'admin',
                    message: 'Hello! I\'m admin.',
                },
                {
                    role: 'user',
                    message: 'Hi!',
                },
                {
                    role: 'admin',
                    message: 'Hello! I\'m admin.',
                },
                {
                    role: 'user',
                    message: 'Hi!',
                },
                {
                    role: 'admin',
                    message: 'Hello! I\'m admin.',
                },
                {
                    role: 'user',
                    message: 'Hi!',
                },
                {
                    role: 'admin',
                    message: 'Hello! I\'m admin.',
                },
                {
                    role: 'user',
                    message: 'Hi!',
                },
            ],
        }),
        methods: {
            isAdmin(role) {
                return role === "admin"
            }
        }
    }
</script>

<style scoped>

</style>