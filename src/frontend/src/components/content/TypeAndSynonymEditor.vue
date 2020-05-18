<template>
    <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="4">
                <v-card class="elevation-12">
                    <v-toolbar color="primary" dark flat>
                        <v-toolbar-title>Register</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                    label="유형"
                                    prepend-icon="mdi-image-filter-none"
                                    type="text"
                                    v-model="type"
                            ></v-text-field>

                            <v-text-field
                                    label="동의어"
                                    prepend-icon="mdi-comment-text-outline"
                                    type="text"
                                    v-model="synonym"
                            ></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="saveTypeAndSynonym">저장</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import API from "../../utils/api";

    export default {
        name: "TypeAndSynonymEditor",
        data() {
            return {
                type: '',
                synonym: '',
            }
        },
        methods: {
            saveTypeAndSynonym() {
                let formData = new FormData();
                formData.append('type', this.type);
                formData.append('synonym', this.synonym);
                API.post('/user/data/type', formData)
                    .then( (res) => {
                        if (res.status === 200) {
                            alert("정상적으로 저장되었습니다.")
                        }
                    }).catch(() => alert("오류가 발생하였습니다."))
            }
        },
    }
</script>

<style scoped>

</style>