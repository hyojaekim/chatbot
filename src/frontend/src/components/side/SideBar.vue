<template>
    <v-card
            height="100%"
            width="256"
            color="black"
    >
        <v-navigation-drawer permanent dark style="border-top-left-radius: 0; border-bottom-left-radius: 0">
            <v-list>
                <v-list-item style="padding-top: 17px">
                    <v-badge v-model="show" overlap style="margin-left: auto; margin-right: auto">
                        <span slot="badge">Admin</span>
                        <v-avatar
                                color="grey lighten-2"
                                size="85"
                                @click.native="show = !show"
                                style="cursor: pointer"
                        >
                            <v-icon dark size="50" color="white">mdi-account-star</v-icon>
                        </v-avatar>
                    </v-badge>
                </v-list-item>
                <v-list-item style="text-align: center">
                    <v-list-item-content>
                        <v-list-item-title class="title">
                            Hyojae Kim
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>

            <v-divider></v-divider>

            <v-list
                    dense
                    nav
            >
                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        link
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
    </v-card>
</template>

<script>
    export default {
        name: "SideBar",
        data () {
            return {
                show: false,
                badgeData: { value: '!' },
                items: [
                    { title: '사용자 데이터', icon: 'mdi-chart-bar' },
                    { title: '타입 및 동의어 조회', icon: 'mdi-label' },
                    { title: '타입 및 동의어 추가', icon: 'mdi-plus-circle-outline' },
                ],
                right: false,
            }
        },
        computed: {
            badge () {
                return this.show ? this.badgeData : null
            }
        }
    }
</script>

<style scoped>

</style>