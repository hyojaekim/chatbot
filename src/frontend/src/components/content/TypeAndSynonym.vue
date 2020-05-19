<template>
    <v-card width="70%">
        <v-card-title>
            유형 및 동의어
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="desserts"
                :search="search"
        ></v-data-table>
    </v-card>
</template>

<script>
    import API from "../../utils/api";

    export default {
        name: "TypeAndSynonym",
        data() {
            return {
                search: '',
                headers: [
                    {text: '유형', value: 'type'},
                    {text: '동의어', value: 'synonym'},
                ],
                desserts: [
                    {
                        id: 0,
                        type: '테스트 타입',
                        synonym: 'test',
                    },
                ],
            }
        },
        created() {
            API.get("/admin/user/data/type")
                .then((res) => {
                    if (res.status === 200) {
                        this.desserts = res.data
                    }
                })
        }
    }
</script>

<style scoped>

</style>