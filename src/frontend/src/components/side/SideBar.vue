<template>
    <v-navigation-drawer
            v-model="drawer"
            :clipped="$vuetify.breakpoint.lgAndUp"
            app
            dark
    >
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
        <v-list dense>
            <template v-for="item in items">
                <v-row
                        v-if="item.heading"
                        :key="item.heading"
                        align="center"
                >
                    <v-col cols="6">
                        <v-subheader v-if="item.heading">
                            {{ item.heading }}
                        </v-subheader>
                    </v-col>
                    <v-col
                            cols="6"
                            class="text-center"
                    >
                        <a
                                href="#!"
                                class="body-2 black--text"
                        >EDIT</a>
                    </v-col>
                </v-row>
                <v-list-group
                        v-else-if="item.children"
                        :key="item.text"
                        v-model="item.model"
                        :prepend-icon="item.model ? item.icon : item['icon-alt']"
                        append-icon=""
                >
                    <template v-slot:activator>
                        <v-list-item-content>
                            <v-list-item-title>
                                {{ item.text }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </template>
                    <v-list-item
                            v-for="(child, i) in item.children"
                            :key="i"
                            @click="showContents(child.contentNumber)"
                            link
                    >
                        <v-list-item-action v-if="child.icon">
                            <v-icon>{{ child.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>
                                {{ child.text }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-group>
                <v-list-item
                        v-else
                        :key="item.text"
                        link
                >
                    <v-list-item-action>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            {{ item.text }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </template>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import {EventBus} from "../../utils/event-bus";

    export default {
        name: "SideBar",
        data() {
            return {
                drawer: true,
                show: false,
                badgeData: {value: '!'},
                props: {
                    source: String,
                },
                items: [
                    {
                        icon: 'mdi-chevron-up',
                        'icon-alt': 'mdi-chart-bar',
                        text: '사용자 데이터',
                        model: false,
                        children: [
                            {
                                text: '조회하기',
                                contentNumber: 1,
                            },
                        ]
                    },
                    {
                        icon: 'mdi-chevron-up',
                        'icon-alt': 'mdi-label',
                        text: '타입 및 동의어',
                        model: false,
                        children: [
                            {
                                text: '조회하기',
                                contentNumber: 2,
                            },
                        ]
                    },
                ],
                right: false,
            }
        },
        computed: {
            badge() {
                return this.show ? this.badgeData : null
            }
        },
        methods: {
            showContents(contentNumber) {
                EventBus.$emit("use-eventbus-show-contents", contentNumber)
            }
        },
        created() {
            EventBus.$on("use-eventbus-toggle-side-bar", () => {
                this.drawer = !this.drawer
            })
        }
    }
</script>

<style scoped>

</style>