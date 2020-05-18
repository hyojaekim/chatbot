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
                <UserData v-if="contentNumber === 1"></UserData>
                <TypeAndSynonym v-else-if="contentNumber === 2"></TypeAndSynonym>
                <TypeAndSynonymEditor v-else-if="contentNumber === 3"></TypeAndSynonymEditor>
                <v-content v-else></v-content>
            </v-row>
        </v-container>
    </v-content>
</template>

<script>
    import UserData from "./UserData";
    import TypeAndSynonym from "./TypeAndSynonym";
    import TypeAndSynonymEditor from "./TypeAndSynonymEditor";
    import { EventBus } from "../../utils/event-bus";

    export default {
        name: "Content",
        components: {TypeAndSynonymEditor, TypeAndSynonym, UserData},
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