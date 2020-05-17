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
    export default {
        name: "TypeAndSynonym",
        data () {
            return {
                search: '',
                headers: [
                    { text: '유형', value: 'type' },
                    { text: '동의어', value: 'synonym' },
                ],
                desserts: [
                    {
                        type: '지하철',
                        synonym: '전철',
                    },
                    {
                        type: '지하철',
                        synonym: '지하철',
                    },
                    {
                        type: '학식',
                        synonym: '학식',
                    },
                    {
                        type: '학식',
                        synonym: '밥',
                    },
                    {
                        type: '학식',
                        synonym: '핛',
                    },
                ],
            }
        },
    }
</script>

<style scoped>

</style>