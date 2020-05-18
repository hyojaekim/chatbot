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
                                    name="type"
                                    prepend-icon="mdi-image-filter-none"
                                    type="text"
                            ></v-text-field>

                            <v-text-field
                                    label="동의어"
                                    name="synonym"
                                    prepend-icon="mdi-comment-text-outline"
                                    type="text"
                            ></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary">저장</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: "CreatorTypeAndSynonym"
    }
</script>

<style scoped>

</style>