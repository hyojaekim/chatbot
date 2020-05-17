<template>
    <v-app id="inspire">
        <SideBar></SideBar>
        <v-app-bar
                :clipped-left="$vuetify.breakpoint.lgAndUp"
                app
                color="grey lighten-5"
        >
            <v-app-bar-nav-icon @click.stop = toggleSideBar></v-app-bar-nav-icon>
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
    import { EventBus } from "./utils/event-bus";

    export default {
        name: 'App',

        components: {
            Contents,
            SideBar,
        },
        methods: {
            toggleSideBar() {
                EventBus.$emit("use-eventbus-toggle-side-bar")
            }
        },
        data: () => ({
        }),
    };
</script>
