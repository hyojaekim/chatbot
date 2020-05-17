<template>
    <v-content style="background-color: WhiteSmoke">
        <v-container
                class="fill-height"
                fluid
        >
            <v-row
                    align="center"
                    justify="center"
            >
                <v-content v-if="contentNumber === 0"></v-content>
                <UserData v-else-if="contentNumber === 1"></UserData>
                <TypeAndSynonym v-else-if="contentNumber === 2"></TypeAndSynonym>
            </v-row>
        </v-container>
    </v-content>
</template>

<script>
    import UserData from "./UserData";
    import TypeAndSynonym from "./TypeAndSynonym";
    import { EventBus } from "../../utils/event-bus";

    export default {
        name: "Content",
        components: {TypeAndSynonym, UserData},
        data() {
            return {
                contentNumber: 0
            }
        },
        created() {
            EventBus.$on("use-eventbus-show-contents", contentNumber => {
                this.contentNumber = contentNumber
            })
        }
    }
</script>

<style scoped>

</style>