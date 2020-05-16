<template>
    <v-card
            height="100%"
            class="overflow-hidden"
    >
        <v-navigation-drawer
                v-model="drawer"
                :color="color"
                :expand-on-hover="expandOnHover"
                :mini-variant="miniVariant"
                :right="right"
                :permanent="permanent"
                absolute
                dark
        >
            <v-list
                    dense
                    nav
                    class="py-0"
            >
                <v-list-item three-line :class="miniVariant && 'px-0'">
                    <v-list-item-avatar>
                        <img src="https://randomuser.me/api/portraits/men/81.jpg">
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title>Admin</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

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
                drawer: true,
                items: [
                    { title: '사용자 데이터', icon: 'mdi-chart-bar' },
                    { title: '타입 및 동의어 조회', icon: 'mdi-tag-multiple' },
                    { title: '타입 및 동의어 추가', icon: 'mdi-playlist-plus' },
                ],
                right: false,
                permanent: true,
                miniVariant: false,
                expandOnHover: false,
                background: false,
            }
        },
    }
</script>

<style scoped>

</style>