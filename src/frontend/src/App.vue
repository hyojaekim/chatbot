<template>
    <v-app id="inspire" v-if="this.role === 'GUEST'">
        <KakaoLogin
                api-key="a562fd4a59d0d9ec2095a74626523238"
                image="kakao_account_login_btn_medium_wide"
                :on-success=onSuccess
                :on-failure=onFailure
        />
    </v-app>
    <v-app id="inspire" v-else-if="this.role === 'USER'">
        <ChattingUserInfoEdit></ChattingUserInfoEdit>
    </v-app>
    <v-app id="inspire" v-else-if="this.role === 'ADMIN'">
        <SideBar></SideBar>
        <v-app-bar
                :clipped-left="$vuetify.breakpoint.lgAndUp"
                app
                color="grey lighten-5"
        >
            <v-app-bar-nav-icon @click.stop=toggleSideBar></v-app-bar-nav-icon>
            <v-toolbar-title
                    style="width: 300px"
                    class="ml-0 pl-4"
            >
                <span class="hidden-sm-and-down">Chatbot Admin</span>
            </v-toolbar-title>
            <v-text-field
                    flat
                    solo-inverted
                    hide-details
                    prepend-inner-icon="mdi-magnify"
                    label="Search"
                    class="hidden-sm-and-down"
                    style="padding-right: 10%"
            ></v-text-field>
        </v-app-bar>
        <Contents></Contents>
    </v-app>
</template>

<script>
    import SideBar from "./components/side/SideBar";
    import Contents from "./components/content/Contents";
    import KakaoLogin from 'vue-kakao-login'
    import {EventBus} from "./utils/event-bus";
    import API from "./utils/api"
    import ChattingUserInfoEdit from "./components/chattting/ChattingUserInfoEdit";

    let onFailure = () => {
        alert("로그인에 실패하였습니다.")
    };

    export default {
        name: 'App',

        components: {
            ChattingUserInfoEdit,
            KakaoLogin,
            Contents,
            SideBar,
        },
        methods: {
            toggleSideBar() {
                EventBus.$emit("use-eventbus-toggle-side-bar")
            },
            onSuccess(data) {
                let accessToken = data.access_token;
                let formData = new FormData();
                formData.append('accessToken', accessToken);
                API.post("/login", formData)
                    .then(response => {
                        if (response.status === 200) {
                            return response.data
                        }
                    }).then(isAdmin => {
                        if (isAdmin) {
                            this.role = "ADMIN"
                        } else {
                            this.role = "USER"
                        }
                    })
            },
            onFailure
        },
        data() {
            return {
                role: "GUEST",
            }
        },
    };
</script>
