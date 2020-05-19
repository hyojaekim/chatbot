<template>
    <v-card width="70%">
        <v-card-title>
            사용자 데이터
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
    import API from "../../utils/api"

    export default {
        name: "UserData",
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: '발화',
                        align: 'start',
                        sortable: false,
                        value: 'text',
                    },
                    { text: '유형', value: 'type' },
                    { text: '동의어', value: 'synonym' },
                    { text: '조회수', value: 'count' },
                ],
                desserts: [
                    {
                        id: 1,
                        text: '임시 데이터',
                        type: '테스트',
                        synonym: '데이터',
                        count: 13,
                    },
                ],
            }
        },
        created() {
            API.get('/admin/user/data')
                .then((res) => {
                    if (res.status === 200) {
                        this.desserts = res.data
                    }
                })
        },
    }
</script>

<style scoped>

</style>